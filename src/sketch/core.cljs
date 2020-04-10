(ns sketch.core
  (:require [goog.dom :as d]))

(defn setup [p]
  (.createCanvas p 640 480))

(defn draw [p]
  (.fill p (if (.-mouseIsPressed p) 0 255))
  (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))

(def parent-id  "example")

(when-not (d/getElement parent-id)
  (d/append js/document.body (d/createDom "div" #js {:id parent-id})))

(def example
  (new js/p5
       (fn [p]
         (set! (.-setup p) (fn [] (setup p)))
         (set! (.-draw p) (fn [] (draw p))))
       parent-id))
