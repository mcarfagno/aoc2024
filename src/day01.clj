(ns aoc)
(require '[clojure.string :as str])

(defn read-input [file] (str/split-lines (slurp file)))

(defn transpose [x] (apply mapv vector x))

(defn silver
  [input]
  (->> input
       ;; re-seq creates a sequence from regex matches
       (map (fn [x] (map parse-long (re-seq #"\d+" x))))
       (transpose)
       (map sort)
       (transpose)
       (map (fn [[x y]]
              (case (compare x y)
                1 (- x y)
                -1 (- y x)
                0 0)))
       (reduce +)))

(defn similarity_score [[l r]] (map #(* % (get (frequencies r) % 0)) l))

(defn gold
  [input]
  (->> input
       (map (fn [x] (map parse-long (re-seq #"\d+" x))))
       (transpose)
       (similarity_score)
       (reduce +)))

(println (silver (read-input "../input/day01.txt")))
(println (gold (read-input "../input/day01.txt")))
