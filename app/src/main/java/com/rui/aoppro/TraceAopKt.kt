package com.rui.aoppro

import android.graphics.Paint.Join
import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import android.view.View
import android.widget.AdapterView
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.JoinPoint.EnclosingStaticPart
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import java.lang.Exception

@Aspect
class TraceAopKt {

    private val TAG = "TraceTagAspectJ"

    @Pointcut("execution(* android.app.Activity.onCreate(..))")
    fun activityOnCreatePointcut() {

    }

    @Pointcut("execution(* android.app.Activity.onResume())")
    fun activityOnResumePointcut() {

    }


    @Pointcut("execution(* android.app.Activity.onPause())")
    fun activityOnPausePointcut() {

    }

    @Pointcut("execution(* android.app.Activity.onDestroy())")
    fun activityOnDestroyPointcut() {

    }


    @After("activityOnPausePointcut()")
    fun trackPause(joinPoint: JoinPoint) {
        PageTrack.trackOnPause(joinPoint)
    }

    @Before("activityOnDestroyPointcut()")
    fun trackDestroy(joinPoint: JoinPoint) {
        PageTrack.trackOnDestroy(joinPoint)
    }

    @After("activityOnResumePointcut()")
    fun trackResume(joinPoint: JoinPoint) {
        PageTrack.trackOnResume(joinPoint)
    }


    @After("activityOnCreatePointcut()")
    fun aopOnCreate(joinPoint: JoinPoint) {
        Log.d(TAG, "after: onCreate in aspectJ $joinPoint");
        PageTrack.trackOnCreate(joinPoint)
    }


    // 最后一次点击的时间
    private var lastTime = 0L

    // 点击间隔时长
    private val INTERVAL = 300L

    @Pointcut("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
    fun onClickPointcut() {
    }

    @Pointcut("execution(* *..*lambda*(..))")
    fun onClickPointLambda() {
    }

    @After("onClickPointcut()||onClickPointLambda()")
    fun afterOnClick(joinPoint: JoinPoint) {
        var target = joinPoint.target
        var className = "";
        if (target != null) {
            className = target.javaClass.simpleName;
        }
        val args = joinPoint.args;
        if (args.isNotEmpty() && args[0] is View) {
            val view = args[0] as View
            val entryName = view.resources.getResourceEntryName(view.id);
//            TrackPoint.onClick(className, entryName);
            Log.d(TAG, "Button clicked $entryName");
        }
//        joinPoint.proceed();
        // 点击事件执行后执行的代码
    }

    @Pointcut("execution(* android.widget.AdapterView.OnItemClickListener.onItemClick(..))")
    fun onListViewItemClick() {
    }

    @Pointcut("execution(* *..lambda$*(android.view.View))")
    fun onLambdaClick() {
        Log.d(TAG, "onClickPointLambda: ")
    }

    @After("onLambdaClick()")
    fun afterLambdaClick() {
        Log.d(TAG, "afterLambdaClick: ")
    }

    @After("onListViewItemClick() && args(parent, view, position, id)")
    fun onItemClick(
        parent: AdapterView<*>,
        view: View,
        position: Int,
        id: Long
    ) {
        // 在这里执行您希望执行的操作，例如记录日志、执行特定的行为等等
        // 这里只是简单地打印点击事件的信息
        Log.d(TAG, "onItemClick: ${view.resources.getResourceEntryName(view.id)}")
        Log.d(TAG, "onItemClick: ListView item clicked at position: $position")
    }



}