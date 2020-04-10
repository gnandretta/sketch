(ns sketch.p5.impl
  (:require [clojure.string :as str]))

(defn dash-case->camel-case [dash-str]
  (let [words (str/split dash-str #"-")]
    (str/join (conj (map str/capitalize (rest words))
                    (first words)))))
