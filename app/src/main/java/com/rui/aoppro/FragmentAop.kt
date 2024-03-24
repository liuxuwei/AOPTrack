package com.rui.aoppro

import android.util.Log
import androidx.fragment.app.Fragment
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class FragmentAop {
    private  val TAG = "FragmentAop"


    // 定义切入点为 Fragment 类的 onCreate 方法
    @org.aspectj.lang.annotation.Pointcut("execution(* androidx.fragment.app.Fragment.onCreate(..)) && target(fragment)")
    fun onCreateMethod(fragment: Fragment) {}

    // 定义切入点为 Fragment 类的 onResume 方法
    @org.aspectj.lang.annotation.Pointcut("execution(* androidx.fragment.app.Fragment.onResume(..)) && target(fragment)")
    fun onResumeMethod(fragment: Fragment) {}

    // 定义切入点为 Fragment 类的 onPause 方法
    @org.aspectj.lang.annotation.Pointcut("execution(* androidx.fragment.app.Fragment.onPause(..)) && target(fragment)")
    fun onPauseMethod(fragment: Fragment) {}


    @org.aspectj.lang.annotation.Pointcut("execution(* androidx.fragment.app.Fragment.onDestroy()) && target(fragment)")
    fun onDestroyMethod(fragment: Fragment) {}

    // 定义切入点为 Fragment 类的 onHiddenChanged 方法
    @org.aspectj.lang.annotation.Pointcut("execution(* androidx.fragment.app.Fragment.onHiddenChanged(..)) && target(fragment) && args(isHidden)")
    fun onHiddenChangedMethod(fragment: Fragment, isHidden: Boolean) {

    }

    // 在 Fragment 的 onCreate 方法之前执行
    @After("onCreateMethod(fragment)")
    fun beforeOnCreate(joinPoint: JoinPoint, fragment: Fragment) {
        // 可以在这里添加你想要执行的代码
        FragmentPageTrack.trackOnCreate(joinPoint)
    }

    // 在 Fragment 的 onResume 方法之后执行
    @After("onResumeMethod(fragment)")
    fun afterOnResume(joinPoint: JoinPoint, fragment: Fragment) {
        // 可以在这里添加你想要执行的代码
        FragmentPageTrack.trackOnResume(joinPoint)
    }

    // 在 Fragment 的 onPause 方法之前执行
    @After("onPauseMethod(fragment)")
    fun beforeOnPause(joinPoint: JoinPoint, fragment: Fragment) {
        // 可以在这里添加你想要执行的代码
        FragmentPageTrack.trackOnPause(joinPoint)
    }


    @Before("onDestroyMethod(fragment)")
    fun afterOnDestroy(joinPoint: JoinPoint, fragment: Fragment) {
        // 可以在这里添加你想要执行的代码
        FragmentPageTrack.trackOnDestroy(joinPoint)
    }

    // 在 Fragment 的 onHiddenChanged 方法之后执行
    @After("onHiddenChangedMethod(fragment, isHidden)")
    fun afterOnHiddenChanged(joinPoint: JoinPoint, fragment: Fragment, isHidden: Boolean) {
        // 可以在这里添加你想要执行的代码
        FragmentPageTrack.trackOnHiddenChange(joinPoint, isHidden)
    }
}