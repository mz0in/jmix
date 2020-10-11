/*
 * Copyright 2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jmix.ui.xml.layout.loader;

import io.jmix.ui.Actions;
import io.jmix.ui.GuiDevelopmentException;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.valuepicker.ValueClearAction;
import io.jmix.ui.component.ActionsHolder;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.HasFormatter;
import io.jmix.ui.component.ValuePicker;
import org.dom4j.Element;

public abstract class AbstractValuePickerLoader<T extends ValuePicker> extends AbstractFieldLoader<T> {

    @Override
    public void loadComponent() {
        super.loadComponent();

        loadTabIndex(resultComponent, element);

        loadActions(resultComponent, element);

        if (resultComponent.getActions().isEmpty()) {
            addDefaultActions();
        }

        loadBuffered(resultComponent, element);
        loadBoolean(element, "fieldEditable", resultComponent::setFieldEditable);

        loadFormatter(resultComponent, element);
    }

    protected void addDefaultActions() {
        Actions actions = getActions();

        resultComponent.addAction(actions.create(ValueClearAction.ID));
    }

    protected Actions getActions() {
        return applicationContext.getBean(Actions.class);
    }

    @Override
    protected Action loadDeclarativeAction(ActionsHolder actionsHolder, Element element) {
        return loadValuePickerDeclarativeAction(actionsHolder, element);
    }

    @Override
    protected void loadFormatter(HasFormatter component, Element element) {
        Element formatterElement = element.element("formatter");
        if (formatterElement == null) {
            return;
        }

        int size = formatterElement.elements().size();
        if (size != 1) {
            throw new GuiDevelopmentException("Only one formatter needs to be defined. " +
                    "The current number of formatters is " + size, getContext(),
                    "Component ID", ((Component) component).getId());
        }

        super.loadFormatter(component, formatterElement);
    }
}
