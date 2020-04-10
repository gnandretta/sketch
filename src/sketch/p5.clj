(ns sketch.p5
  (:require [clojure.string :as str]))

(defn- dash-case->camel-case [dash-str]
  (let [words (str/split dash-str #"-")]
    (str/join (conj (map str/capitalize (rest words))
                    (first words)))))

(defn- method-form->method-spec [[name & args-and-body]]
  [(str name) (conj args-and-body 'clojure.core/fn)])

(defmacro defsketch [name & method-forms]
  (let [parent-id (str name)
        methods-spec (mapv method-form->method-spec method-forms)]
    `(do (ensure-parent ~parent-id)
         (def ~name (instance ~methods-spec ~parent-id)))))

(defmacro defp5fn [name]
  (let [p5-name (-> name str dash-case->camel-case)]
    `(defn ~name [& ~'args]
       (apply* ~p5-name ~'args))))
