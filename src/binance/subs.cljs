(ns binance.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::current-data
 (fn [db]
   (:current-data db)))

(re-frame/reg-sub
 ::prev-data
 (fn [cofx]
   (:prev-data cofx)))

(re-frame/reg-sub
 ::time
 (fn [db]
   (:time db)))
