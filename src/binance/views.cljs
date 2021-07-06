(ns binance.views
  (:require
   [reagent.core :as r]
   [re-frame.core :as re-frame]
   [binance.subs :as subs]))
(def current (r/atom "ALL"))
(def display (r/atom #{"ALL"}))
(defn grid [data]
  [:div.container
   [:div.grid-detail
    [:p
     [:h2 "Symbol"]]
    [:p
     [:h2 "Price"]]]
   [:div.data
    (for [x @data]
      [:div.price {:key (str (:symbol x))}
       [:p (str (:symbol x))]
       [:p (str (:price x))]])]])

(defn main-panel []
  (let [curr (re-frame/subscribe [::subs/current-data])
        prev (re-frame/subscribe [::subs/prev-data])]
    (fn []
       (grid curr))))
