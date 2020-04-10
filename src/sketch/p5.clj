(ns sketch.p5
  (:require [sketch.p5.impl :as impl]))

(defn- method-form->method-spec [[name & args-and-body]]
  [(str name) (conj args-and-body 'clojure.core/fn)])

(defmacro defsketch [name & method-forms]
  (let [parent-id (str name)
        methods-spec (mapv method-form->method-spec method-forms)]
    `(do (ensure-parent ~parent-id)
         (or (defonce ~name (instance ~methods-spec ~parent-id))
             (set-methods ~name ~methods-spec)))))

(defn- build-p5fn-arity
  ([p5-name params] (build-p5fn-arity p5-name params params))
  ([p5-name clj-params p5-params]
   (list clj-params
         (list 'sketch.p5.apply* p5-name p5-params))))

(defmacro defp5fn [name & params]
  (let [p5-name (-> name str impl/dash-case->camel-case)
        build (partial build-p5fn-arity p5-name)]
    `(defn ~name ~@(cond (empty? params) (build '[& params] 'params)
                         (= (count params) 1) (build (first params))
                         :else (map build params)))))
