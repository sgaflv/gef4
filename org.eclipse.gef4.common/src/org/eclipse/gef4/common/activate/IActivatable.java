/*******************************************************************************
 * Copyright (c) 2014, 2016 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Nyßen (itemis AG) - initial API and implementation
 *     
 * Note: Parts of this interface have been transferred from org.eclipse.gef.EditPart.
 *     
 *******************************************************************************/
package org.eclipse.gef4.common.activate;

import org.eclipse.gef4.common.adapt.IAdaptable;

import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 * An {@link IActivatable} represents an entity that can be activated (
 * {@link #activate()}) and deactivated ({@link #deactivate()}) as required.
 * <p>
 * The current activation state of an {@link IActivatable} (whether the entity
 * is active or not) can be queried by clients ({@link #isActive()}) and changes
 * to it can be observed via the {@link #activeProperty() active property}
 * <p>
 * Any client implementing this interface may internally use an
 * {@link ActivatableSupport} as a delegate to easily realize the required
 * functionality.
 * 
 * @author anyssen
 * 
 */
public interface IActivatable {

	/**
	 * The name of the {@link #activeProperty() active property}.
	 */
	public static String ACTIVE_PROPERTY = "active";

	/**
	 * Activates the {@link IActivatable}. It is expected that a call to
	 * {@link IActivatable#isActive()} returns {@code true} after this method
	 * has been called (unless {@link #deactivate()} is called to deactivate the
	 * {@link IActivatable}).
	 */
	public void activate();

	/**
	 * A read-only property providing information about the active state if this
	 * {@link IActivatable}.
	 * 
	 * @return A read-only boolean property which is {@code true} in case the
	 *         {@link IActivatable} is active, {@code false} otherwise.
	 */
	public ReadOnlyBooleanProperty activeProperty();

	/**
	 * Deactivates the {@link IActivatable}. It is expected that a call to
	 * {@link IActivatable#isActive()} return {@code false} after this method
	 * has been called (unless {{@link #activate()} is called to re-activate the
	 * {@link IAdaptable}.
	 */
	public void deactivate();

	/**
	 * Reports whether this {@link IActivatable} is active or inactive, which
	 * resembles the value of the {@link #activeProperty() active property}.
	 * 
	 * @return {@code true} in case the {@link IActivatable} is active,
	 *         {@code false} otherwise.
	 */
	public boolean isActive();

}