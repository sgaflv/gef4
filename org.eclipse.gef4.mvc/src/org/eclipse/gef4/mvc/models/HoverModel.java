/*******************************************************************************
 * Copyright (c) 2014, 2016 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthias Wienand (itemis AG) - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.gef4.mvc.models;

import java.beans.PropertyChangeEvent;

import org.eclipse.gef4.mvc.parts.IContentPart;
import org.eclipse.gef4.mvc.parts.IVisualPart;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The {@link HoverModel} is used to store the current viewer's mouse hover
 * target, i.e. the {@link IVisualPart} that is currently under the mouse
 * cursor.
 *
 * @author mwienand
 *
 * @param <VR>
 *            The visual root node of the UI toolkit used, e.g.
 *            javafx.scene.Node in case of JavaFX.
 */
public class HoverModel<VR> {

	/**
	 * The {@link HoverModel} fires {@link PropertyChangeEvent}s when the
	 * hovered part changes. This is the name of the property that is delivered
	 * with the event.
	 */
	final public static String HOVER_PROPERTY = "hover";

	private ObjectProperty<IVisualPart<VR, ? extends VR>> hoverProperty = new SimpleObjectProperty<>(
			this, HOVER_PROPERTY);

	/**
	 * Sets the hovered part to <code>null</code>.
	 * <p>
	 * Fires a {@link PropertyChangeEvent}.
	 */
	public void clearHover() {
		setHover(null);
	}

	/**
	 * Returns the currently hovered {@link IContentPart} or <code>null</code>
	 * if no visual part is hovered.
	 *
	 * @return the currently hovered {@link IContentPart} or <code>null</code>
	 */
	public IVisualPart<VR, ? extends VR> getHover() {
		return hoverProperty.get();
	}

	/**
	 * Returns an object property representing the current hover part.
	 *
	 * @return A property named {@link #HOVER_PROPERTY}.
	 */
	public ObjectProperty<IVisualPart<VR, ? extends VR>> hoverProperty() {
		return hoverProperty;
	}

	/**
	 * Sets the hovered {@link IVisualPart} to the given value. The given part
	 * may be <code>null</code> in order to unhover.
	 * <p>
	 * Fires a {@link PropertyChangeEvent}.
	 *
	 * @param cp
	 *            hovered {@link IVisualPart} or <code>null</code>
	 */
	public void setHover(IVisualPart<VR, ? extends VR> cp) {
		hoverProperty.set(cp);
	}

}
