(define (problem shakey_prob1)
  (:domain shakey)
  (:objects
    room-1 room-2 room-3
    door-1 door-2 door-3

    lswitch-1 lswitch-2 lswitch-3

    box-1 box-2 shakey leftArm rightArm

    ball-1 ball-2 ball-3 ball-4
    ball-5 ball-6 ball-7 ball-8

    )

  (:init
    (connected room-1 room-2 door-1) (connected room-2 room-1 door-1)
    (connected room-2 room-3 door-2) (connected room-3 room-2 door-2)
    (connected room-2 room-3 door-3) (connected room-3 room-2 door-3)
    (in lswitch-1 room-1) (switchable lswitch-1)
    (in lswitch-2 room-2) (switchable lswitch-2);(not(turnedOn))
    (in lswitch-3 room-3) (switchable lswitch-3);(not(turnedOn))
    (wide door-1) (wide door-3)

    (in shakey room-1) (movable shakey)
    (arm leftArm) (arm rightArm) (free leftArm) (free rightArm)

    (in box-1 room-2) (pushable box-1) (climbable box-1)
    (in box-2 room-3) (pushable box-2)

    (in ball-1 room-1) (carriable ball-1)
    (in ball-2 room-1) (carriable ball-2)
    (in ball-3 room-2) (carriable ball-3)
    (in ball-4 room-2) (carriable ball-4)
    (in ball-5 room-1) (carriable ball-5)
    (in ball-6 room-1) (carriable ball-6)
    (in ball-7 room-2) (carriable ball-7)
    (in ball-8 room-2) (carriable ball-8)

    )

  (:goal (and(in ball-1 room-3)
    (in ball-2 room-3)
    (in ball-3 room-3)
    (in ball-4 room-3)
    (in ball-5 room-1)
    (in ball-6 room-2)
    (in ball-7 room-1)
    (in ball-8 room-2)
    (in box-1 room-3)
    (in box-2 room-1)
    (in shakey room-3))

    )

  ;(:goal (and (turnedOn lswitch-1)
  ;  (turnedOn lswitch-2)
  ;  (turnedOn lswitch-3)
  ;  (in box room-1)
  ;  (in shakey room-3)))

)
