(ns sketch.p5
  (:require [goog.object :as o]
            [goog.dom :as d]))

(defn- set-methods [p spec]
  (doseq [[name f] spec]
    (o/set p name (fn [] (f p)))))

(defn instance [methods-spec parent-id]
  (new js/p5
       (fn [p] (set-methods p methods-spec))
       parent-id))

(defn ensure-parent [id]
  (when-not (d/getElement id)
    (d/append js/document.body (d/createDom "div" #js {:id id}))))
