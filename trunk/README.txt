-------------------------------------------------------------------------------
-- LICENCE
-------------------------------------------------------------------------------

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.


-------------------------------------------------------------------------------
-- 1. SETTING UP (!!! IMPORTANT !!!!)
-------------------------------------------------------------------------------

If you're reading this file I'll assume you've already checked the source code out of SVN.
If not do so by running this:
  svn checkout http://gwt-html-editor.googlecode.com/svn/trunk/ gwt-html-editor
(assuming you have Subversion installed)

Modify project.properties to fit your configuration
  1. gwt.os -- your OS (linux, windows, mac)
  2. gwt.installation.dir -- path to GWT installation
  
     if you don't have GWT installed download it from:
     http://code.google.com/webtoolkit/
     
-------------------------------------------------------------------------------
-- 2. COMPILING
-------------------------------------------------------------------------------

cd gwt-html-editor
ant
# or 
ant jar

This will generate the jar file in build directory that you can use. 
Refer to http://code.google.com/p/gwt-html-editor/wiki/GettingStarted for more details.

-------------------------------------------------------------------------------
-- 3. TEST/SAMPLE APP
-------------------------------------------------------------------------------

cd gwt-html-editor
ant gwt-test-compile

This will compile the test/sample app into:
  gwt-html-editor/build/web/com.jpavel.gwt.wysiwyg.test.Test
Just open Test.html in a browser.

Run "ant gwt-test-shell" to run the test/sample app in GWT Shell (hosted mode).

-------------------------------------------------------------------------------
-- 4. QUESTIONS?
-------------------------------------------------------------------------------

Project home - http://code.google.com/p/gwt-html-editor/
Wiki - http://code.google.com/p/gwt-html-editor/w/list
Support group - http://groups.google.com/group/gwt-rich-text-editor

-------------------------------------------------------------------------------
-- 5. DEVELOPERS
-------------------------------------------------------------------------------

Code style:
 http://code.google.com/webtoolkit/makinggwtbetter.html#codestyle
