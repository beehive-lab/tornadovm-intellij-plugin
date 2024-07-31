/*
 * Copyright (c) 2023, APT Group, Department of Computer Science,
 *  The University of Manchester.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package uk.ac.manchester.beehive.tornado.plugins.dynamicInspection;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiMethod;
import uk.ac.manchester.beehive.tornado.plugins.util.TornadoTWTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class DynamicInspection {
    public static void process(Project project, ArrayList<PsiMethod> methodArrayList, ArrayList<PsiMethod> others, Map<String, Object> fields){
        try {
            CodeGenerator.fileCreationHandler(project, methodArrayList, others, fields, TornadoTWTask.getImportCodeBlock());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
