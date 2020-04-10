(ns sketch.core
  (:require [sketch.p5 :as p5]))

(def parent-id  "example")

(p5/ensure-parent parent-id)

(def example
  (p5/instance
   [["setup" (fn [p]
               (.createCanvas p 640 480))]
    ["draw" (fn [p]
              (.fill p (if (.-mouseIsPressed p) 0 255))
              (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))]]
   parent-id))
