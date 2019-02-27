(ns chrepl.popup.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.core.async :refer [<!]]
            [clojure.browser.event :refer [listen]]
            [chromex.ext.tabs :as tabs]
            [chromex.logging :refer-macros [log info warn error group group-end]]
            [chromex.protocols.chrome-port :refer [post-message!]]
            [chromex.ext.runtime :as runtime :refer-macros [connect]]))

; -- a message loop ---------------------------------------------------------------------------------------------------------

(defn process-message! [message]
  (log "POPUP: got message:" message))

(defn run-message-loop! [message-channel]
  (log "POPUP: starting message loop...")
  (go-loop []
    (when-some [message (<! message-channel)]
      (process-message! message)
      (recur))
    (log "POPUP: leaving message loop")))

(defn connect-to-background-page! []
  (let [background-port (runtime/connect)]
    (post-message! background-port "hello from POPUP!")
    (run-message-loop! background-port)))

(defn send-button-clicked-to-background-page! []
  (let [background-port (runtime/connect)]
    (post-message! background-port (pr-str {:cmd :inject}))
    (run-message-loop! background-port)))

; (defn inject-to-tab! [tab]
;   (let [background-port (runtime/connect)]
;     (post-message! background-port (pr-str {:cmd :inject :tab tab}))
;     (run-message-loop! background-port)))

(def inject-btn (.getElementById js/document "inject"))

; (defn send-for-tab [tab]
;   (go
;     (inject-to-tab! (<! (tabs/query (clj->js {:active true :currentWindow true}))))))

(when inject-btn
  (listen inject-btn "click" send-button-clicked-to-background-page!))


; -- main entry point -------------------------------------------------------------------------------------------------------

(defn init! []
  (log "POPUP: init")
  (connect-to-background-page!))
