/*******************************************************************************
 * CogTool Copyright Notice and Distribution Terms
 * CogTool 1.3, Copyright (c) 2005-2013 Carnegie Mellon University
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License (see LGPL.txt). 
 * 
 * CogTool is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * 
 * CogTool is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with CogTool; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * CogTool makes use of several third-party components, with the 
 * following notices:
 * 
 * Eclipse SWT
 * Eclipse GEF Draw2D
 * 
 * Unless otherwise indicated, all Content made available by the Eclipse 
 * Foundation is provided to you under the terms and conditions of the Eclipse 
 * Public License Version 1.0 ("EPL"). A copy of the EPL is provided with this 
 * Content and is also available at http://www.eclipse.org/legal/epl-v10.html.
 * 
 * CLISP
 * 
 * Copyright (c) Sam Steingold, Bruno Haible 2001-2006
 * This software is distributed under the terms of the FSF Gnu Public License.
 * See COPYRIGHT file in clisp installation folder for more information.
 * 
 * ACT-R 6.0
 * 
 * Copyright (c) 1998-2007 Dan Bothell, Mike Byrne, Christian Lebiere & 
 *                         John R Anderson. 
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License (see LGPL.txt).
 * 
 * Apache Jakarta Commons-Lang 2.1
 * 
 * This product contains software developed by the Apache Software Foundation
 * (http://www.apache.org/)
 * 
 * jopt-simple
 * 
 * Copyright (c) 2004-2013 Paul R. Holser, Jr.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Mozilla XULRunner 1.9.0.5
 * 
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/.
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * 
 * The J2SE(TM) Java Runtime Environment
 * 
 * Copyright 2009 Sun Microsystems, Inc., 4150
 * Network Circle, Santa Clara, California 95054, U.S.A.  All
 * rights reserved. U.S.  
 * See the LICENSE file in the jre folder for more information.
 ******************************************************************************/

package edu.cmu.cs.hcii.cogtool.model;

import edu.cmu.cs.hcii.cogtool.util.ObjectLoader;
import edu.cmu.cs.hcii.cogtool.util.ObjectSaver;

public class TextActionSegment extends AScriptStep
{
    public static final int edu_cmu_cs_hcii_cogtool_model_TextActionSegment_version = 2;

    protected static final String segmentVAR = "segment";
    protected static final String localizedPrefixVAR = "localizedPrefix";

    // To read old files
    protected static final String transitionVAR = "transition";

    // No longer needed to read old files
//  protected static final String actionVAR = "action";
//  protected static final String actionFocusVAR = "actionFocus";

    private static ObjectSaver.IDataSaver<TextActionSegment> SAVER =
        new ObjectSaver.ADataSaver<TextActionSegment>() {
            @Override
            public int getVersion()
            {
                return edu_cmu_cs_hcii_cogtool_model_TextActionSegment_version;
            }

            @Override
            public void saveData(TextActionSegment v, ObjectSaver saver)
                throws java.io.IOException
            {
                saver.saveString(v.segment, segmentVAR);
                saver.saveString(v.localizedPrefix, localizedPrefixVAR);
            }
        };

    public static void registerSaver()
    {
        ObjectSaver.registerSaver(TextActionSegment.class.getName(), SAVER);
    }

    private static ObjectLoader.IObjectLoader<TextActionSegment> LOADER =
        new ObjectLoader.AObjectLoader<TextActionSegment>() {
            @Override
            public void set(TextActionSegment target, String variable, Object value)
            {
                if (variable != null) {
                    // Happens only for version 0
                    if (variable.equals(transitionVAR)) {
                        Transition transition = (Transition) value;

                        if (transition != null) {
                            target.insertedByUser = true;
                            target.owner = new TransitionScriptStep(transition);
                        }
                    }
//                  else if (variable.equals(actionVAR)) {
//                      IGNORED
//                  }
//                  else if (variable.equals(actionFocusVAR)) {
//                      IGNORED
//                  }
                    else if (variable.equals(segmentVAR)) {
                        target.segment = (String) value;
                    }
                    else if (variable.equals(localizedPrefixVAR)) {
                        target.localizedPrefix = (String) value;
                    }
                }
            }

            @Override
            public void set(TextActionSegment target, String variable, boolean value)
            {
                if (variable != null) {
                    // Happens only for version 1
                    if (variable.equals(insertedByUserVAR)) {
                        target.insertedByUser = value;
                    }
                }
            }

            @Override
            public TextActionSegment createObject()
            {
                return new TextActionSegment();
            }
        };

    public static void registerLoader()
    {
        ObjectLoader.registerLoader(TextActionSegment.class.getName(),
                                    edu_cmu_cs_hcii_cogtool_model_TextActionSegment_version,
                                    LOADER);
        ObjectLoader.registerLoader(TextActionSegment.class.getName(),
                                    1,
                                    LOADER);
        ObjectLoader.registerLoader(TextActionSegment.class.getName(),
                                    0,
                                    LOADER);
    }

    /**
     * The transformed substring of the action.
     */
    protected String segment;

    /**
     * The prefix for fetching the step's localized string
     */
    protected String localizedPrefix;

    // For loading purposes
    protected TextActionSegment()
    {
        demonstrated = true;   // by definition!
    }

    // These are always demonstrated.
    public TextActionSegment(AScriptStep ownerStep,
                             String partial,
                             boolean userInserted,
                             String prefix)
    {
        super(ownerStep, true);

        owner = ownerStep;
        segment = partial;
        insertedByUser = userInserted;
        localizedPrefix = prefix;

        if (ownerStep.getDefiner(WidgetAttributes.APPENDED_TEXT_ATTR) == ownerStep)
        {
            setAttribute(WidgetAttributes.APPENDED_TEXT_ATTR, partial);
        }
    }

    public String getText()
    {
        return segment;
    }

    @Override
    public String getLocalizedString()
    {
        return localizedPrefix + " '" + segment + "'";
    }

    /**
     * Get the focus on which this script step performs.  May return
     * <code>null</code> if the action is independent of any focus.
     */
    @Override
    public TransitionSource getStepFocus()
    {
        return owner.getStepFocus();
    }

    @Override
    public Frame getDestinationFrame()
    {
        if (insertedByUser) {
            return owner.getDestinationFrame();
        }

        return super.getDestinationFrame();
    }

    @Override
    public boolean usesFrame(Frame frame)
    {
        return owner.usesFrame(frame);
    }

    @Override
    public boolean usesTransition(Transition t)
    {
        return owner.usesTransition(t);
    }

    /**
     * Create a "deep" copy of this generated script step (i.e., one that is
     * not an owner itself.
     * <p>
     * It is the responsibility of the caller to "place" the copy
     * (usually by adding it to an Demonstration).
     *
     * @param duplicateScope used to find design components referred to by the
     *                       step duplicate
     * @param ownerScope used to find the owner of the duplicate generated step
     * @return the script step copy
     * @author mlh
     */
    @Override
    public AScriptStep duplicate(TaskApplication.DemoDuplicateScope duplicateScope,
                                 AScriptStep.GeneratedStepDuplicateScope ownerScope)
    {
        AScriptStep copy =
            new TextActionSegment(ownerScope.getOwner(owner),
                                  segment,
                                  insertedByUser,
                                  localizedPrefix);

        copyState(copy);

        return copy;
    }

    @Override
    public void accept(AScriptStep.ScriptStepVisitor visitor)
    {
        visitor.visit(this);
    }
}
