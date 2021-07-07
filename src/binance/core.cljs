(ns binance.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [binance.events :as events]
   [binance.views :as views]
   [clojure.core.async
    :refer [go-loop
            timeout
            <!]]))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch [:request])
  (go-loop []
    (<! (timeout 10000))
    (re-frame/dispatch [:request])
    (recur))
  (mount-root))
