(ns chrepl.background
  (:require-macros [chromex.support :refer [runonce]])
  (:require [chrepl.background.core :as core]))

(runonce
  (core/init!))
