/*******************************************************************************
 * Copyright (c) 2022 Red Hat Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.ls.core.internal.cleanup;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.manipulation.CleanUpContextCore;
import org.eclipse.jdt.core.manipulation.ICleanUpFixCore;
import org.eclipse.jdt.internal.corext.fix.CodeStyleFixCore;

/**
 * Represents a clean up that prefixes all (non-static) field and method
 * accesses with {@code this}.
 */
public class MemberAccessUsesThisCleanUp implements ISimpleCleanUp {

	public MemberAccessUsesThisCleanUp() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ls.core.internal.cleanup.ISimpleCleanUp#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "qualifyMembers";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ls.core.internal.cleanup.ISimpleCleanUp#createFix()
	 */
	@Override
	public ICleanUpFixCore createFix(CleanUpContextCore context) throws CoreException {
		CompilationUnit unit = context.getAST();
		if (unit == null) {
			return null;
		}
		return CodeStyleFixCore.createCleanUp(unit, //
				true, //
				false, //
				false, //
				false, //
				true, //
				false, //
				false, //
				false);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ls.core.internal.cleanup.ISimpleCleanUp#getRequiredCompilerMarkers()
	 */
	@Override
	public List<String> getRequiredCompilerMarkers() {
		return Collections.emptyList();
	}

}