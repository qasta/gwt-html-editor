var __FCK_load_listeners = new Array();

function __FCK_addLoadListener(listener) {
	__FCK_load_listeners.push(listener);
}

function FCKeditor_OnComplete() {
	for (var i = 0; i < __FCK_load_listeners.length; i++) {
		__FCK_load_listeners[i]();
	}
}