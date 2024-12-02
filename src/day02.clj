(ns aoc)
(require '[clojure.string :as str])

(defn read-input [file] (str/split-lines (slurp file)))

(defn level
  [[x y]]
  (case (compare x y)
    1 (if (<= 1 (abs (- x y)) 3) 1 0)
    -1 (if (<= 1 (abs (- x y)) 3) -1 0)
    0 0))

(defn safety [x] (apply = (map level (partition 2 1 x)))) ;TODO: check nonzero as well

(defn silver
  [input]
  (->> input
       (map (fn [x] (map parse-long (re-seq #"\d+" x))))
       (filter safety)
       (count)))

(println (silver (read-input "../input/day02.txt")))
