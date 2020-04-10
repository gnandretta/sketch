(ns sketch.p5
  (:require [goog.object :as o]
            [goog.dom :as d])
  (:require-macros [sketch.p5 :refer [defp5fn]]))

(def ^:dynamic *sketch* nil)

(defn- set-methods [p spec]
  (doseq [[name f] spec]
    (o/set p name (fn []
                    (binding [*sketch* p]
                      (f p))))))

(defn instance [methods-spec parent-id]
  (new js/p5
       (fn [p] (set-methods p methods-spec))
       parent-id))

(defn ensure-parent [id]
  (when-not (d/getElement id)
    (d/append js/document.body (d/createDom "div" #js {:id id}))))

(defn- apply* [p5fn-name args]
  (let [o (or *sketch* js/window)]
    (.apply (o/get o p5fn-name) o (into-array args))))

(defp5fn create-canvas)
(defp5fn fill)
(defp5fn ellipse)
