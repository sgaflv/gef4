/*******************************************************************************
 * Copyright (c) 2014, 2016 itemis AG and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Nyßen (itemis AG) - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.gef4.mvc.tests.fx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.eclipse.gef4.common.reflect.ReflectionUtils;
import org.eclipse.gef4.fx.listeners.VisualChangeListener;
import org.eclipse.gef4.mvc.fx.parts.AbstractFXContentPart;
import org.eclipse.gef4.mvc.fx.parts.AbstractFXHandlePart;
import org.eclipse.gef4.mvc.fx.parts.AbstractFXRootPart;
import org.eclipse.gef4.mvc.parts.IVisualPart;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * Tests for {@link AbstractFXHandlePart}.
 *
 * @author anyssen
 *
 */
public class AbstractFXHandlePartTests {

	@BeforeClass
	public static void initializeJavaFXToolkit() throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				// initialize JavaFX toolkit indirectly
				new JFXPanel();
			}
		});
	}

	private AbstractFXRootPart<Group> rp = new AbstractFXRootPart<Group>() {

		@Override
		protected void addChildVisual(IVisualPart<Node, ? extends Node> child, int index) {
			getVisual().getChildren().add(index, child.getVisual());
		}

		@Override
		protected Group createVisual() {
			return new Group();
		}

		@Override
		protected void doRefreshVisual(Group visual) {
			// nothing to do
		};

		@Override
		protected void removeChildVisual(IVisualPart<Node, ? extends Node> child, int index) {
			getVisual().getChildren().remove(index);
		};
	};

	private AbstractFXContentPart<Node> cp = new AbstractFXContentPart<Node>() {
		@Override
		protected Node createVisual() {
			return new Group();
		}

		@Override
		protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
			return HashMultimap.create();
		}

		@Override
		protected List<? extends Object> doGetContentChildren() {
			return Collections.emptyList();
		}

		@Override
		protected void doRefreshVisual(Node visual) {
			// nothing to do
		}
	};

	private AbstractFXHandlePart<Node> hp = new AbstractFXHandlePart<Node>() {

		@Override
		protected Node createVisual() {
			return new Group();
		}

		@Override
		protected void doRefreshVisual(Node visual) {
			// nothing to do
		}
	};

	protected Map<IVisualPart<Node, ? extends Node>, Integer> getAnchorageLinkCount(AbstractFXHandlePart<Node> hp) {
		return ReflectionUtils.getPrivateFieldValue(hp, "anchorageLinkCount");
	}

	protected Map<IVisualPart<Node, ? extends Node>, VisualChangeListener> getVisualChangeListeners(
			AbstractFXHandlePart<Node> hp) {
		return ReflectionUtils.getPrivateFieldValue(hp, "visualChangeListeners");
	}

	/**
	 * Tests that an {@link AbstractFXHandlePart} only registers a single
	 * {@link VisualChangeListener} per anchorage (even if there are several
	 * anchorage links with different roles), and that this listener is not
	 * removed before all links are removed again.
	 */
	@Test
	public void testProperRegistrationOfVisualChangeListeners() {
		// ensure the visual of both parts have a common ancestor (the
		// root part
		// visual)
		rp.addChild(cp);
		rp.addChild(hp);
		assertNull(getAnchorageLinkCount(hp).get(cp));
		assertEquals(0, getVisualChangeListeners(hp).size());
		// check we have a single visual change listener after anchoring
		// the handle part
		hp.attachToAnchorage(cp, "r1");
		assertEquals(1, getAnchorageLinkCount(hp).get(cp).intValue());
		assertEquals(1, getVisualChangeListeners(hp).size());
		// check we still have only a single change listener after
		// anchoring the
		// same handle part with a different role
		hp.attachToAnchorage(cp, "r2");
		assertEquals(2, getAnchorageLinkCount(hp).get(cp).intValue());
		assertEquals(1, getVisualChangeListeners(hp).size());
		// check we still have a visual change listener, even if one
		// anchorage
		// is removed
		hp.detachFromAnchorage(cp, "r2");
		assertEquals(1, getAnchorageLinkCount(hp).get(cp).intValue());
		assertEquals(1, getVisualChangeListeners(hp).size());
		// ensure no visual change listener is registered any more
		hp.detachFromAnchorage(cp, "r1");
		assertNull(getAnchorageLinkCount(hp).get(cp));
		assertEquals(0, getVisualChangeListeners(hp).size());
		// re-attach and assure the map is intialized again
		hp.attachToAnchorage(cp, "r1");
		assertEquals(1, getAnchorageLinkCount(hp).get(cp).intValue());
		assertEquals(1, getVisualChangeListeners(hp).size());
	}

}
