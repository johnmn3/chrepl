// var CLOSURE_UNCOMPILED_DEFINES = {"figwheel.repl.connect_url":"ws:\/\/localhost:4999\/figwheel-connect?fwbuild=app.chrepl"};
var CLOSURE_NO_DEPS = true;

var load_all = function() {
  var base = "http://localhost:4999/compiled/content-script/";
  function load(url, cb) {
    var e = document.createElement("script");
    document.head.appendChild(e);
    e.onload = cb;
    e.src = base + url;
  }
  load("goog/base.js", function() {
    load("goog/deps.js", function() {
      load("cljs_deps.js", function() {
        load("launch.js", function() {
          console.log("loaded...");
        })
      });
    });
  });
}

load_all();
