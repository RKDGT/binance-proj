(ns binance.views
  (:require
   [re-frame.core :as re-frame]
   [binance.subs :as subs]))
(defn grid [data time]
  [:div.container
   [:div.grid-detail
    [:h2 "Symbol"]
    [:h2 "Price"]]
   [:div
    (.slice (str  @time) 0 24)]
   [:div.data
    (for [x @data]
      [:div.price {:key (str (:symbol x))}
       [:p (str (:symbol x))]
       [:p (str (:price x))]])]])

(defn main-panel []
  (let [curr (re-frame/subscribe [::subs/current-data])
        time (re-frame/subscribe [::subs/time])]
    (fn []
       (grid curr time))))
