/*
 * Copyright 2022 Haulmont.
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

package io.jmix.flowui.kit.meta;

import com.vaadin.flow.component.HasComponents;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Inherited
public @interface StudioComponent {

    String name() default "";

    /**
     * Fully-qualified name of component class
     */
    String classFqn() default "";

    String category() default "";

    String icon() default "io/jmix/flowui/kit/meta/icon/unknownComponent.svg";

    String xmlElement() default "";

    String xmlns() default "";

    String xmlnsAlias() default "";

    /**
     * Describes the available place in the hierarchy.
     * By default, components can be located inside layout or inside a component inherited from {@link HasComponents}
     */
    String availablePlaceRegExp() default "((^(mainView/appLayout)?((/drawerLayout)|(/navigationBar)|(/initialLayout)))$)|(^view/layout$)" +
            "|((^(mainView/appLayout)?((/drawerLayout)|(/navigationBar)|(/initialLayout))|(^view/layout))?(/hasComponents)*$)";

    StudioProperty[] properties() default {};

    StudioPropertiesBinding[] propertiesBindings() default {};

    StudioSupplyHandler[] supplyHandlers() default {};

    StudioAvailableChildrenInfo availableChildren() default @StudioAvailableChildrenInfo();
}
