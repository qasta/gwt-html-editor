# Installation #

Installation steps:
  * Download (or compile) the latest JAR file
  * add the downloaded JAR file to classpath of the GWT Compiler/Shell
  * add: 

&lt;inherits name='com.gc.gwt.wysiwyg.Editor'/&gt;

 to your module descriptor XML file

**Note:** for older versions (<= 0.1.2)
  * add: 

&lt;inherits name='com.jpavel.gwt.wysiwyg.Editor'/&gt;

 to your module descriptor XML file

# Sample Code #

```
  HorizontalPanel hzContainer = new HorizontalPanel();

  Editor editor = new Editor();
  editor.setWidth("100%");
  editor.setHeight("300px");

  editor.setHTML("<h1>Hello World!</h1>");

  editor.addLoadListener(new LoadListener() {
     public void onLoad(Widget sender) {
        // do something if you want...
     }

     public void onError(Widget sender) {
     }
  });

  hzContainer.add(editor);
```

**NOTE:** editor.load() method has been removed.

# Upgrading from 0.1.2 to 0.1.3 #

Update you GWT module descriptor file to include
> 

&lt;inherits name='com.gc.gwt.wysiwyg.Editor'/&gt;



instead of

> 

&lt;inherits name='com.jpavel.gwt.wysiwyg.Editor'/&gt;



# Known Issues #

[see the Known Issues page](KnownIssues.md)







