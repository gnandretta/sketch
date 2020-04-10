(ns sketch.core
  (:require [sketch.p5 :as p5]))

(defn setup [p]
  (.createCanvas p 640 480))

(defn draw [p]
  (.fill p (if (.-mouseIsPressed p) 0 255))
  (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))

(def parent-id  "example")

(p5/ensure-parent parent-id)

(def example
  (p5/instance [["setup" setup] ["draw" draw]] parent-id))
