package be.matttbe.deutschebankbelgiumrootbypasser;

/**
 * Deutsche Bank Belgium Root Bypasser
 * Copyright (C) 2017  matttbe
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Inspired by https://github.com/Razer2015/BNPParibasFortisRootBypasser
 */


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XC_MethodReplacement.returnConstant;

public class BypassRootDetection implements IXposedHookLoadPackage {
	private static final String TAG = "DBBeRootBypass";
	private static final String PACKAGE_NAME = "com.db.pbc.mybankbelgium";
	private static final String UTIL_CLASS = "com.db.pbc.mybank.be.utility.Util";
	private static final String IS_ROOTED_METHOD = "isRootedVasco";

	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

		/* Limit to our app */
		if (!lpparam.packageName.equals(PACKAGE_NAME))
			return;

		XposedBridge.log(TAG + ": Bypassing root detection for " + PACKAGE_NAME);

		/* Force returned value 0 for method to check Root status */
		findAndHookMethod(UTIL_CLASS,
				lpparam.classLoader,
				IS_ROOTED_METHOD,
				android.content.Context.class,
				returnConstant(0));
	}
}
