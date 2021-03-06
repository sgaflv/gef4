/*******************************************************************************
 * Copyright (c) 2016 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Nyßen (itemis AG) - initial API and implementation
 *******************************************************************************/
/**
 * This package augments the property API of JavaFX by adding support related to
 * {@link com.google.common.collect.SetMultimap} and
 * {@link com.google.common.collect.Multiset}.
 * <p>
 * It also provides replacements for
 * {@link javafx.beans.property.ReadOnlyMapWrapper},
 * {@link javafx.beans.property.SimpleMapProperty},
 * {@link javafx.beans.property.ReadOnlySetWrapper}, and
 * {@link javafx.beans.property.ReadOnlyListWrapper}, which fix the following
 * issues:
 * <ul>
 * <li>https://bugs.openjdk.java.net/browse/JDK-8136465: fixed by keeping track
 * of all listeners and ensuring that remaining listeners are re-added when a
 * listener is removed.</li>
 * <li>https://bugs.openjdk.java.net/browse/JDK-8089557: fixed by not forwarding
 * listeners to the read-only property but rather keeping the lists of listeners
 * distinct.</li>
 * </ul>
 * 
 * @author anyssen
 */
package org.eclipse.gef4.common.beans.property;