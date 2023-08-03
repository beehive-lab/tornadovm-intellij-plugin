package com.tais.tornado_plugins.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBFont;
import com.tais.tornado_plugins.util.TornadoTWTask;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TaskParametersDialogWrapper extends DialogWrapper {
    private ArrayList<PsiMethod> methodsList;
    private ArrayList<JBLabel> labelArrayList;
    private JPanel dialogPanel;
    private HashMap<String, JBTextField> textFieldsList;
    public TaskParametersDialogWrapper(ArrayList<PsiMethod> methodsList) {
        super(true);
        setTitle("Method Parameters");
        this.methodsList = methodsList;
        labelArrayList = new ArrayList<>();
        textFieldsList = new HashMap<>();
        for (PsiMethod method:methodsList) {
            String methodName = TornadoTWTask.psiMethodFormat(method);
            labelArrayList.add(new JBLabel(methodName));
            for (PsiParameter parameter: method.getParameterList().getParameters()) {
                textFieldsList.put(methodName+parameter.getText(), new JBTextField());
            }
        }
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        dialogPanel = new JPanel();
        VerticalFlowLayout layout = new VerticalFlowLayout();
        VerticalFlowLayout layout1 = new VerticalFlowLayout(VerticalFlowLayout.CENTER);
        layout1.setHPadding(20);
        dialogPanel.setLayout(layout);
        for (PsiMethod method:methodsList){
            String methodName = TornadoTWTask.psiMethodFormat(method);
            dialogPanel.add(new JBLabel(methodName + ":"));
            JPanel panel = new JPanel();
            panel.setLayout(layout1);
            for (PsiParameter parameter: method.getParameterList().getParameters()) {
                panel.add(new JBLabel(Objects.requireNonNull(parameter.getText())+":"));
                panel.add(textFieldsList.get(methodName+parameter.getText()));
            }
            dialogPanel.add(panel);
        }
        return dialogPanel;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        for (PsiMethod method:methodsList) {
            String methodName = TornadoTWTask.psiMethodFormat(method);
            for (PsiParameter parameter: method.getParameterList().getParameters()) {
                System.out.println(textFieldsList.get(methodName + parameter.getText()).getText());
            }
        }
        return super.doValidate();
    }
}
