(ns aoc)
(require '[clojure.string :as str])

(defn silver
  [input]
  (->> input
       (re-seq #"[mul(](\d{1,3})[,](\d{1,3})[)]")
       (map rest) ;; remove match
       (map #(* (Integer/parseInt (first %)) (Integer/parseInt (second %))))
       (reduce +)))

(println (silver (slurp "../input/day03.txt")))
