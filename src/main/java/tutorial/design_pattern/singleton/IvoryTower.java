/*
 * Copyright 2017 JiaweiMao jiaweiM_philo@hotmail.com
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

package tutorial.design_pattern.singleton;

/**
 * Singleton class. Eagerly initialized static instance guarantees thread safety.
 */
public final class IvoryTower
{
    /**
     * Static to class instance of the class.
     */
    private static final IvoryTower INSTANCE = new IvoryTower();

    /**
     * Private constructor so nobody can instantiate the class.
     */
    private IvoryTower() {}

    /**
     * To be called by user to obtain instance of the class.
     *
     * @return instance of the singleton.
     */
    public static IvoryTower getInstance()
    {
        return INSTANCE;
    }
}