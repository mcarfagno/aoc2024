(ns aoc)
(require '[clojure.string :as str])

(defn read-input [file] (str/split-lines (slurp file)))

(defn level
  [[x y]]
  (case (compare x y)
    1 (if (<= 1 (abs (- x y)) 3) 1 0)
    -1 (if (<= 1 (abs (- x y)) 3) -1 0)
    0 0))

(defn safety [x] (let [levels (map level (partition 2 1 x))] (apply = levels)))  ;TODO: check nonzero as well

(defn silver
  [input]
  (->> input
       (map (fn [x] (map parse-long (re-seq #"\d+" x))))
       (filter safety)
       (count)))

(defn remove-first
  [x coll]
  (let [[pre post] (split-with #(not= x %) coll)] (concat pre (rest post))))

(defn remove-one-element-combinations
  [coll]
  (for [x coll :let [rest (remove-first x coll)]] rest))

(defn gold
  [input]
  (->> input
       (map (fn [x] (map parse-long (re-seq #"\d+" x))))
       (map remove-one-element-combinations)
       (map #(map safety %))
       (map #(some true? %))
       (filter #(not (nil? %)))
       (count)))

(println (silver (read-input "../input/day02.txt")))
(println (gold (read-input "../input/day02.txt")))
