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
package org.eclipse.gef4.mvc.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef4.geometry.planar.Dimension;
import org.eclipse.gef4.mvc.parts.IResizableContentPart;
import org.eclipse.gef4.mvc.parts.ITransformableContentPart;

/**
 * An {@link ITransactionalOperation} to change the size of an
 * {@link IResizableContentPart}.
 *
 * @author anyssen
 *
 * @param <VR>
 *            The visual root node of the UI toolkit this
 *            {@link ITransformableContentPart} is used in, e.g.
 *            javafx.scene.Node in case of JavaFX.
 */
public class ResizeContentOperation<VR> extends AbstractOperation
		implements ITransactionalOperation {

	private final IResizableContentPart<VR, ? extends VR> resizableContentPart;
	private Dimension initialSize;
	private Dimension finalSize;

	/**
	 * Creates a new {@link ResizeContentOperation} to resize the content of the
	 * given {@link IResizableContentPart}.
	 *
	 * @param resizableContentPart
	 *            The part to resize.
	 * @param initialSize
	 *            The initial size before applying the change.
	 * @param finalSize
	 *            The final size after applying the change.
	 */
	public ResizeContentOperation(
			IResizableContentPart<VR, ? extends VR> resizableContentPart,
			Dimension initialSize, Dimension finalSize) {
		super("Resize Content");
		this.resizableContentPart = resizableContentPart;
		this.initialSize = initialSize;
		this.finalSize = finalSize;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		resizableContentPart.resizeContent(finalSize);
		return Status.OK_STATUS;
	}

	@Override
	public boolean isContentRelevant() {
		return true;
	}

	@Override
	public boolean isNoOp() {
		return initialSize.equals(finalSize);
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return execute(monitor, info);
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		resizableContentPart.resizeContent(initialSize);
		return Status.OK_STATUS;
	}
}