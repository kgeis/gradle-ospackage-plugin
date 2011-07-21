/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trigonic.gradle.plugins.rpm

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertSame
import static org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Test

class RpmCopySpecVisitorTest {
    RpmCopySpecVisitor visitor;
    
    @Before
    public void setup() {
        visitor = new RpmCopySpecVisitor();
    }
    
    @Test
    public void withoutUtils() {
        File script = resourceFile("script.sh")
        Object result = visitor.scriptWithUtils(null, script)
        assertSame script, result
    }
    
    @Test
    public void withUtils() {
        Object result = visitor.scriptWithUtils(resourceFile("utils.sh"), resourceFile("script.sh"))
        assertTrue result instanceof String
        assertEquals "#!/bin/bash\nfunction hello() {\n    echo 'Hello, world.'\n}\n#!/bin/bash\nhello\n", result
    }
    
    File resourceFile(String name) {
        new File(getClass().getResource(name).getPath())
    }
}