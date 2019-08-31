(require '[clojure.string :as str])

(defn ConvertArraytoString
[arrayin width height]
(loop [x 0] (when (< x height)
    (loop [y 0](when (< y width)
    (if (= (aget arrayin x y) \-)
      (print "-"))
    (if (= (aget arrayin x y) \#)
      (print "#"))
    (if (= (aget arrayin x y) \@)
      (print "@"))
    (if (= (aget arrayin x y) \!)
      (print "!"))
    (if (= (aget arrayin x y) \+)
      (print "+"))
    (recur (+ y 1)));when y
  );loop y
  (println "")
  (recur (+ x 1)));when x
);loop x
);ConvertArraytoString

(defn Foutput
[flag farray width height MPath destr destc]
(println "\n")
(if (= flag true)
  (do
    (println "Woo hoo, I found the treasure :-) \n")
    (def size(count MPath))
    (loop [x 0] (when (< x size)
        (def pos (nth MPath x))
        (def i (nth pos 0))
        (def j (nth pos 1))
        (aset farray i j \+)
      (recur (+ x 1)));when
    );loop
    (aset farray destr destc \@));do
);if
(if (not= flag true)
  (println "Uh oh, I could not find the treasure :-( \n"));if
(ConvertArraytoString farray width height)
);Foutput

(defn PrintPath
[visited puzzle width height flag MPath destr destc]
(def farray(make-array Character/TYPE height width))
(loop [x 0] (when (< x height)
    (loop [y 0](when (< y width)
      (if(= (aget puzzle x  y) \#)
        (do
          (aset farray x y \#));do
      );if
      (if(= (aget puzzle x  y) \-)
        (do
          (aset farray x y \-));do
      );if
      (if (and (= (aget puzzle x  y) \-) (= (aget visited x y) false))
        (do
          (aset farray x y \!));do
      );if
      (if(= (aget puzzle x  y) \@)
        (do
          (aset farray x y \@));do
      );if
      (recur (+ y 1)));when y
    );loop y
    (recur (+ x 1)));when x
  );loop x
  (Foutput flag farray width height MPath destr destc)
);PrintPath

(defn MovePath
[puzzle visited Path destr destc width height]
(def FPath Path)
(def size(count FPath))
(def pos 0)
(aset visited 0 0 false)
(def parentx(make-array Long/TYPE height width))
(def parenty(make-array Long/TYPE height width))
(aset parentx 0 0 -1)
(aset parenty 0 0 -1)
(def MPath [[0,0]])
(def flag false)
(while (not= size 0)
  (do
    (def front (nth FPath 0))
    (def i (nth front 0))
    (def j (nth front 1))
    (def len (count FPath))
    (def flag1 false)

    (if (and (= i destr) (= j destc))
      (do
        (def flag true)
        (def flag1 true)
        (def size 0)
      );do
    ); destination found
    (if (and (and (>= (- i 1) 0) (= (aget visited (- i 1) j) true)) (not= flag1 true))
      (do
        (def FPath(cons [(- i 1) j] FPath))
        (def flag1 true)
        (def size (count FPath));updated size
        (aset visited (- i 1) j false)
        (aset parentx (- i 1) j i)
        (aset parenty (- i 1) j j)
      );do
    );moving up
    (if (and (and (< (+ i 1) height) (= (aget visited (+ i 1) j) true)) (not= flag1 true))
      (do
        (def FPath(cons [(+ i 1) j] FPath))
        (def flag1 true)
        (def size (count FPath));updated size
        (aset visited (+ i 1) j false)
        (aset parentx (+ i 1) j i)
        (aset parenty (+ i 1) j j)
      );do
    );moving down
    (if (and (and (>= (- j 1) 0) (= (aget visited i (- j 1)) true)) (not= flag1 true))
      (do
         (def FPath(cons [i (- j 1)] FPath))
         (def flag1 true)
        (def size (count FPath));updated size
        (aset visited i (- j 1) false)
        (aset parentx i (- j 1) i)
        (aset parenty i (- j 1) j)
      );do
    );moving left
    (if (and (and (< (+ j 1) width) (= (aget visited i (+ j 1)) true)) (not= flag1 true))
      (do
        (def FPath(cons [i (+ j 1)] FPath))
        (def flag1 true)
        (def size (count FPath));updated size
        (aset visited i (+ j 1) false)
        (aset parentx i (+ j 1) i)
        (aset parenty i (+ j 1) j)
      );do
    );moving right

    (if (= flag1 false)
     (do(def FPath(rest FPath))
     (def size (count FPath))));if
    );do
);while
(def m destr)
(def n destc)
(while (and (not= m -1) (not= n -1))
(do
    (def MPath(conj MPath [m n]))
    (def l (aget parentx m n))
    (def q (aget parenty m n))
    (def m l)
    (def n q)
  );do
);while
(PrintPath visited puzzle width height flag MPath destr destc)
);MovePath

(defn FindPath
[puzzle visited destr destc width height]
(def sourcer 0)
(def sourcec 0)
(def Path [[sourcer,sourcec]])
(MovePath puzzle visited Path destr destc width height)
)

(defn MakeArray [str1 width height]
(def size(* height width))
(def puzzle(make-array Character/TYPE height width))
(def visited(make-array Boolean/TYPE height width))
  (def l1 (str/split str1 #"")) ;split each character from the string
  (def l2 (str/replace str1 #"\s{1,}" "")) ;replacing spaces
  (def a (vec l2))
  (def count1 -1)
  (loop [x 0] (when (< x height)
      (loop [y 0](when (< y width)
          (def count1 (+ count1 1))
          (aset puzzle x y (get a count1))
          (if (= (aget puzzle x y) \@)
            (do(def destr x)
            (def destc y)));if ends
            (if (or (= (aget puzzle x y) \-) (= (aget puzzle x y) \@))
              (aset visited x y true)
              (aset visited x y false));if ends
          (recur (+ y 1)));when y
      );loop y
      (recur (+ x 1)));when x
  );loop x
  (println "\nThis is my challenge: \n")
  (ConvertArraytoString puzzle width height)
  (FindPath puzzle visited destr destc width height)
);MakeArray

(defn ReadFile []
(def st2(slurp "map.txt"))
(def str1 (str/split-lines st2))
(def widthpuz 0)
(def heightpuz 0)
(doseq [line str1]
  (def heightpuz (+ heightpuz 1))
  (def len(count line))
  (if ( > len widthpuz)
    (def widthpuz len))
  );doseq
(doseq [line1 str1]
  (def len1(count line1))
  (if (< len1 widthpuz)
    (do
      (println "\nMap entered is an invalid map!\n")
      (System/exit 0)
      );do
  );if
  );doseq
(MakeArray st2 widthpuz heightpuz)
);readfile

(defn Main []
(ReadFile)
)

(Main)
