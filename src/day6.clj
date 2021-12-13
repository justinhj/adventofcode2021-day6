(ns day6
  (:require
   [clojure.string :as str]))
   
(defn parse-input 
  "Parse the input and return the list of fish (numbers)."
  [filename]
  (let [str (slurp (format "resources/%s" filename))
        nums (str/split str #",|\n")]
    (map #(Integer/parseInt %) nums)))

(defn run [opts]
  (println (parse-input "example.txt")))

