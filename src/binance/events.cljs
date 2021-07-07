(ns binance.events
  (:require
   [reagent.core :as r]
   [re-frame.core :as re-frame]
   [binance.db :as db]
   [ajax.core :as ajax]
   [day8.re-frame.http-fx]))

(def default-uri (r/atom "https://www.binance.com/api/v3/ticker/price"))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-fx
 :request
 (fn []
   {:http-xhrio {:uri @default-uri
                 :method :get
                 :timeout 10000
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [:process-response]
                 :on-failure [:bad-response]}}))

(re-frame/reg-event-db
 :process-response
 (fn [db [_ response]]
   (-> db
    (assoc :current-data (js->clj response))
    (assoc :time (js/Date.)))))

(re-frame/reg-event-db
 :bad-response
 (fn [_ [_ response]]
   (println response)))
