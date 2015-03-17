Starting from version 0.1.2 GWT Rich Text Editor Widget started to partially support Safari. Basic editing functions work such as: bold, italic, align left/right/center/justify, etc.
Un/ordered lists, font style and size, text colors don't work.

Un/ordered lists don't work because InsertOrderedList and InsertUnorderedList commands are not supported in Safari, yet.

But the main issue is with the loosing of focus off the WYSIWYG IFrame. I was able to overcome this problem for basic buttons by adding:
```
    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);
        DOM.eventPreventDefault(event);
    }
```

but still not sure what to do with drop-downs and dialog boxes...
Any ideas?