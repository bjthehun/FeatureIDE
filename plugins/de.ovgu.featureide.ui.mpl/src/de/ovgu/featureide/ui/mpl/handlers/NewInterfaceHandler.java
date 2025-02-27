/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2019  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 *
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.ui.mpl.handlers;

import static de.ovgu.featureide.fm.core.localization.StringTable.NEW_INTERFACES;

import java.util.Collection;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import de.ovgu.featureide.core.IFeatureProject;
import de.ovgu.featureide.core.mpl.MPLPlugin;
import de.ovgu.featureide.fm.ui.wizards.WizardConstants;
import de.ovgu.featureide.ui.handlers.base.AFeatureProjectHandler;
import de.ovgu.featureide.ui.wizards.NewInterfaceWizard;

public class NewInterfaceHandler extends AFeatureProjectHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void singleAction(IFeatureProject project) {
		final NewInterfaceWizard wizard = new NewInterfaceWizard(NEW_INTERFACES);
		final WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
		if (dialog.open() == Window.OK) {
			MPLPlugin.getDefault().createInterface(project.getProject(), (IFeatureProject) wizard.getData(WizardConstants.KEY_OUT_PROJECT),
					(Collection<String>) wizard.getData(WizardConstants.KEY_OUT_FEATURES));
		}
	}

}
