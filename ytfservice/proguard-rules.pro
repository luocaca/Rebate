# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile




-dontshrink


-optimizationpasses 5
-dontusemixedcaseclassnames#混淆时不会大小写混合类名
-dontskipnonpubliclibraryclasses #指定不去忽略非公共的库类
-dontpreverify #不预校验
-dontwarn #不警告
-verbose
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* #优化配置
-dontoptimize #不优化
-ignorewarnings #忽略警告


-dontwarn com.google.gson.**
-keep class sun.misc.Unsafe { *; }
-keep class com.idea.fifaalarmclock.entity.***
-keep class com.google.gson.** { *; }





#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}



-keep class me.luocaca.ytfservice.entity.** {*;}



-keep class * extends java.lang.annotation.Annotation {*;}
-keepattributes *Annotation*,InnerClasses  	# 保持注解 保留Annotation不混淆
-keep class android.support.annotation.Keep
-keep @android.support.annotation.Keep class * {*;}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}
