(ns binance.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))
(re-frame/reg-fx
  :change-filter
  (fn [db _ changed]
     (assoc-in db [:filter] changed)))
(re-frame/reg-sub
 ::filter
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
