(ns day6
  (:require
   [clojure.string :as str]))
   
(defn parse-input 
  "Parse the input and return the list of fish (numbers)."
  [filename]
  (let [str (slurp (format "resources/%s" filename))
        nums (str/split str #",|\n")]
    (map #(Integer/parseInt %) nums)))

(defn day-in-the-life
  "Simulate a day in the life of the fish."
  [fish]
  (let [[updated-fish count]
        (reduce
         (fn [[fishes newborncount] fish]
           (if (= 0 fish)
             [(conj fishes 6)
              (inc newborncount)]
             [(conj fishes (dec fish))
              newborncount]))
         [[] 0]
         fish)]
    (concat updated-fish (repeat count 8))))

(defn live-life
  "Live the fish life for the specified number of days."
  [fish days]
  (loop [fish fish
         days days]
    (if (= days 0)
      fish
      (do
       (recur (day-in-the-life fish) (dec days))))))

(defn part1
  "Solve part 1, live the fish life for 80 days"
  []
  (let [days 80
        fish (parse-input "day6.txt")
        final-fish (live-life fish days)
        ]
    (println (format "Number of fish after %d days is %d" days (count final-fish)))))

(defn group-fish
  "Group the fish by age and count"
  [fish]
  (map
   (fn [[k v]]
     [k (count v)])
   (group-by identity fish)))

(defn fish-live-fast
  "Takes the map of fish age and counts and ages them all"
  [fish-ages]
  (let [[new-fish-ages reborn]
        (reduce
         (fn [[acc reborn] [age cnt]]
           (if (= age 0)
             [(concat acc [8 cnt]) cnt]
             [(concat acc [(dec age) cnt]) reborn]))
         [[] 0]
         fish-ages)
        mappified (apply hash-map new-fish-ages)]
    (update mappified 6
            (fn [f]
              (if (nil? f)
                reborn
                (+ f reborn))))))

(defn life-in-the-fast-lane
  "Fish living large"
  [fish days]
  (let [fish-ages (group-fish fish)]
    (loop [fish fish-ages
           days days]
      (if (= days 0)
        (reduce (fn [acc [k v]]
                  (+ acc v)) 0 fish)
        (do
          (recur (fish-live-fast fish) (dec days)))))))

(defn part2
  "Whew those fish are growing too fast"
  []
  (let [days 256
        fish (parse-input "day6.txt")
        final-fish (life-in-the-fast-lane fish days)
        ]
    (println (format "Number of fish after %d days is %d" days final-fish))))

(defn run [opts]
  "Do it"
  (do
    (part1)
    (part2)))

