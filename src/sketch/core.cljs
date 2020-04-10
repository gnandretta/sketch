(ns sketch.core
  (:require [sketch.p5 :as p5]))

(p5/defsketch example
  (setup [p]
    (.createCanvas p 640 480))
  (draw [p]
    (.fill p (if (.-mouseIsPressed p) 0 255))
    (.ellipse p (.-mouseX p) (.-mouseY p) 80 80)))

(comment
  (macroexpand-1 '(p5/defsketch example
                    (setup [p]
                      (.createCanvas p 640 480))
                    (draw [p]
                      (.fill p (if (.-mouseIsPressed p) 0 255))
                      (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))))

  ;; yields

  (do (sketch.p5/ensure-parent "example")
      (def example (sketch.p5/instance
                    [["setup" (clojure.core/fn [p]
                                (.createCanvas p 640 480))]
                     ["draw" (clojure.core/fn [p]
                               (.fill p (if (.-mouseIsPressed p) 0 255))
                               (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))]]
                    "example"))))
