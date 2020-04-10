(ns sketch.core
  (:require [sketch.p5 :as p5]))

(p5/defsketch example
  [["setup" (fn [p]
              (.createCanvas p 640 480))]
   ["draw" (fn [p]
             (.fill p (if (.-mouseIsPressed p) 0 255))
             (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))]])
(comment
  (macroexpand-1 '(p5/defsketch example
                    [["setup" (fn [p]
                                (.createCanvas p 640 480))]
                     ["draw" (fn [p]
                               (.fill p (if (.-mouseIsPressed p) 0 255))
                               (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))]]))
  ;; yields

  (do (sketch.p5/ensure-parent "example")
      (def example (sketch.p5/instance
                    [["setup" (fn [p]
                                (.createCanvas p 640 480))]
                     ["draw" (fn [p]
                               (.fill p (if (.-mouseIsPressed p) 0 255))
                               (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))]]
                    "example"))))
