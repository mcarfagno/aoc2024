(ns aoc)
(require '[clojure.string :as str])

(defn read-input [file] (str/split-lines (slurp file)))

(defn transpose [x] (apply mapv vector x))

(defn solve1
  [input]
  (->> input
       (map (fn [x] (map parse-long (str/split x #"\s+")))) ;read vect from
                                                            ;str
       (transpose)
       (map sort) ;order
       (transpose) ; now we tuple of smallests
       (map (fn [[frst scnd]]
              (case (compare frst scnd)
                1 (- frst scnd)
                -1 (- scnd frst)
                0 0)))
       (reduce +)))

(println (solve1 (read-input "../input/day01.txt")))
