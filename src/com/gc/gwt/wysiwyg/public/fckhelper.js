var __FCK_load_listeners = new Array();
var __FCK_focus_listeners = new Array();
var __FCK_blur_listeners = new Array();

function __FCK_addLoadListener(listener) {
	__FCK_load_listeners.push(listener);
}

function __FCK_addFocusListener(listener) {
	__FCK_focus_listeners.push(listener);
}

function __FCK_addBlurListener(listener) {
	__FCK_blur_listeners.push(listener);
}

function FCKeditor_OnComplete(editorInstance) {
	for (var i = 0; i < __FCK_load_listeners.length; i++) {
		__FCK_load_listeners[i](editorInstance);
	}
	
	editorInstance.Events.AttachEvent('OnFocus', function() {
		for (var i = 0; i < __FCK_focus_listeners.length; i++) {
			if (editorInstance.Name == __FCK_focus_listeners[i].Name) {
				__FCK_focus_listeners[i].Callback();
			}
		}
    });
    
    editorInstance.Events.AttachEvent('OnBlur', function() {
		for (var i = 0; i < __FCK_blur_listeners.length; i++) {
			if (editorInstance.Name == __FCK_blur_listeners[i].Name) {
				__FCK_blur_listeners[i].Callback();
			}
		}
    });
}