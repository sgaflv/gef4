/*******************************************************************************
 * Copyright (c) 2016 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Nyßen (itemis AG) - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.gef4.mvc.ui.parts;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A factory for the creation of an {@link ISelectionProvider}.
 *
 * @author anyssen
 *
 */
public interface ISelectionProviderFactory {

	/**
	 * Creates a new {@link ISelectionProvider} for the given
	 * {@link IWorkbenchPart}.
	 *
	 * @param workbenchPart
	 *            The {@link IWorkbenchPart} the {@link ISelectionProvider} is
	 *            related to.
	 * @return A new {@link ISelectionProvider} instance.
	 */
	public ISelectionProvider create(IWorkbenchPart workbenchPart);

}
