(ns chrepl.popup
  (:require-macros [chromex.support :refer [runonce]])
  (:require [chrepl.popup.core :as core]))

(runonce
  (core/init!))
