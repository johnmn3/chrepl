(ns chrepl.load
  (:require [figwheel.repl :as repl]))

(repl/connect "ws://localhost:4999/figwheel-connect?fwbuild=app.chrepl")
