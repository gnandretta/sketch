(ns sketch.p5
  (:require [sketch.p5.impl :as impl]
            [goog.object :as o]
            [goog.dom :as d])
  (:require-macros [sketch.p5 :refer [defp5fn]]))

(def ^:dynamic *sketch* nil)

(defn prop
  ([name] (prop name nil))
  ([name not-found] (o/get (or *sketch* js/window)
                           (impl/dash-case->camel-case (clojure.core/name name))
                           not-found)))

(deftype Params [state]
  ILookup
  (-lookup [this k] (-lookup this k nil))
  (-lookup [_ k not-found] (get @state k (prop k not-found))))

(defn- set-method [p name f]
  (o/set p name (fn [& args]
                  (binding [*sketch* p]
                    (apply f args)))))

(defn- set-methods [p spec]
  (doseq [[name f] spec]
    (set-method p name (case name
                         "draw" (fn []
                                  (when-let [update (.-update *sketch*)]
                                    (swap! (.. *sketch* -params -state)
                                           update
                                           (.-params *sketch*)))
                                  (f (.-params *sketch*)))
                         f))))

(defn instance [methods-spec parent-id]
  (new js/p5
       (fn [p]
         (o/set p "params" (Params. (atom {})))
         (set-methods p methods-spec))
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
