
package com.rahanavand.rnv;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class MainActivity extends Activity {

    private RnvView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(7,21,34));
        getWindow().setNavigationBarColor(Color.rgb(7,21,34));
        view = new RnvView();
        setContentView(view);
    }

    private class RnvView extends View {

        final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Path path = new Path();

        final int BG = Color.rgb(5,16,27);
        final int CARD = Color.rgb(12,31,47);
        final int TEXT = Color.rgb(239,247,250);
        final int MUTED = Color.rgb(158,180,191);
        final int CYAN = Color.rgb(45,196,220);
        final int CYAN2 = Color.rgb(23,129,164);
        final int GREEN = Color.rgb(77,210,151);
        final int ORANGE = Color.rgb(255,178,92);
        final int LINE = Color.rgb(34,64,79);

        float d, downX, downY;
        int page = 0;

        RnvView() {
            super(MainActivity.this);
            d = getResources().getDisplayMetrics().density;
            setFocusable(true);
            setContentDescription("راهاناوند، صفحه اصلی");
        }

        float dp(float v) {
            return v * d;
        }

        void txt(Canvas c, String s, float x, float y, float size,
                 int color, boolean bold, Paint.Align a) {

            p.setShader(null);
            p.setStyle(Paint.Style.FILL);
            p.setColor(color);
            p.setTextSize(dp(size));
            p.setTypeface(Typeface.create(
                    "sans",
                    bold ? Typeface.BOLD : Typeface.NORMAL
            ));
            p.setTextAlign(a);
            c.drawText(s, x, y, p);
        }

        void rr(Canvas c, float l, float t, float r, float b,
                float rad, int color) {

            p.setShader(null);
            p.setStyle(Paint.Style.FILL);
            p.setColor(color);

            c.drawRoundRect(
                    new RectF(l, t, r, b),
                    rad,
                    rad,
                    p
            );
        }

        void gr(Canvas c, float l, float t, float r, float b, float rad) {

            p.setShader(
                    new LinearGradient(
                            l,
                            t,
                            r,
                            b,
                            CYAN2,
                            Color.rgb(23,83,116),
                            Shader.TileMode.CLAMP
                    )
            );

            p.setStyle(Paint.Style.FILL);

            c.drawRoundRect(
                    new RectF(l, t, r, b),
                    rad,
                    rad,
                    p
            );

            p.setShader(null);
        }

        @Override
        protected void onDraw(Canvas c) {

            c.drawColor(BG);

            header(c);

            if (page == 0) {
                home(c);
            } else if (page == 1) {
                people(c);
            } else if (page == 2) {
                projects(c);
            } else if (page == 3) {
                market(c);
            } else {
                more(c);
            }

            nav(c);
        }

        void header(Canvas c) {

            float w = getWidth();

            txt(
                    c,
                    "راهاناوند",
                    w - dp(24),
                    dp(48),
                    25,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    "RAHANAVAND  •  RNV",
                    w - dp(24),
                    dp(72),
                    11,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );

            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(dp(3));
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setColor(CYAN);

            path.reset();

            float x = dp(38);
            float y = dp(52);

            path.moveTo(x - dp(15), y);

            path.cubicTo(
                    x - dp(15),
                    y - dp(12),
                    x - dp(4),
                    y - dp(12),
                    x,
                    y
            );

            path.cubicTo(
                    x + dp(4),
                    y + dp(12),
                    x + dp(15),
                    y + dp(12),
                    x + dp(15),
                    y
            );

            path.cubicTo(
                    x + dp(15),
                    y - dp(12),
                    x + dp(4),
                    y - dp(12),
                    x,
                    y
            );

            path.cubicTo(
                    x - dp(4),
                    y + dp(12),
                    x - dp(15),
                    y + dp(12),
                    x - dp(15),
                    y
            );

            c.drawPath(path, p);

            p.setStyle(Paint.Style.FILL);
        }

        void home(Canvas c) {

            float l = dp(18);
            float r = getWidth() - dp(18);

            gr(
                    c,
                    l,
                    dp(98),
                    r,
                    dp(235),
                    dp(24)
            );

            txt(
                    c,
                    "یک شبکه، هزار مسیر رشد",
                    r - dp(20),
                    dp(133),
                    23,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    "آدم‌ها • مهارت‌ها • کار • بازار • دانش",
                    r - dp(20),
                    dp(160),
                    13,
                    Color.rgb(205,235,241),
                    false,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    "هر مشارکت می‌تواند به ارزش واقعی تبدیل شود.",
                    r - dp(20),
                    dp(185),
                    12,
                    Color.WHITE,
                    false,
                    Paint.Align.RIGHT
            );

            rr(
                    c,
                    r - dp(154),
                    dp(201),
                    r - dp(20),
                    dp(225),
                    dp(13),
                    Color.argb(38,255,255,255)
            );

            txt(
                    c,
                    "شروع مسیر  →",
                    r - dp(87),
                    dp(219),
                    12,
                    TEXT,
                    true,
                    Paint.Align.CENTER
            );

            txt(
                    c,
                    "دسترسی سریع",
                    r,
                    dp(266),
                    18,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            float gap = dp(10);
            float cw = (r - l - gap * 2) / 3f;

            quick(
                    c,
                    l,
                    dp(282),
                    cw,
                    "افراد",
                    "همکاری",
                    "●",
                    CYAN
            );

            quick(
                    c,
                    l + cw + gap,
                    dp(282),
                    cw,
                    "کار",
                    "پروژه",
                    "◆",
                    GREEN
            );

            quick(
                    c,
                    l + (cw + gap) * 2,
                    dp(282),
                    cw,
                    "بازار",
                    "تأمین",
                    "◈",
                    ORANGE
            );

            txt(
                    c,
                    "نبض راهاناوند",
                    r,
                    dp(390),
                    18,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            rr(
                    c,
                    l,
                    dp(405),
                    r,
                    dp(510),
                    dp(20),
                    CARD
            );

            metric(
                    c,
                    l + dp(55),
                    dp(432),
                    "افراد",
                    "∞",
                    CYAN
            );

            metric(
                    c,
                    l + dp(172),
                    dp(432),
                    "مهارت",
                    "∞",
                    GREEN
            );

            metric(
                    c,
                    l + dp(289),
                    dp(432),
                    "فرصت",
                    "∞",
                    ORANGE
            );

            txt(
                    c,
                    "سیستم برای محدود کردن انسان طراحی نشده؛ برای وصل کردن اوست.",
                    r - dp(32),
                    dp(485),
                    10,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    "هسته‌های اصلی",
                    r,
                    dp(548),
                    18,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            row(
                    c,
                    l,
                    dp(565),
                    r,
                    "آموزش و استعداد",
                    "یادگیری → مهارت → مسیر کار",
                    "01",
                    CYAN
            );

            row(
                    c,
                    l,
                    dp(625),
                    r,
                    "کار و پروژه",
                    "نیرو + منابع + کیفیت + درآمد",
                    "02",
                    GREEN
            );

            row(
                    c,
                    l,
                    dp(685),
                    r,
                    "دانش و روش کار",
                    "ثبت تجربه و پیدا کردن روش بهتر",
                    "03",
                    ORANGE
            );

            row(
                    c,
                    l,
                    dp(745),
                    r,
                    "امنیت و اعتماد",
                    "حریم خصوصی + ثبت رویداد + هشدار",
                    "04",
                    CYAN
            );

            txt(
                    c,
                    "دسترسی‌پذیری: متن، تصویر، صدا و مسیرهای ساده",
                    r,
                    dp(820),
                    11,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );
        }

        void quick(
                Canvas c,
                float x,
                float y,
                float w,
                String a,
                String b,
                String icon,
                int col
        ) {

            rr(
                    c,
                    x,
                    y,
                    x + w,
                    y + dp(82),
                    dp(18),
                    CARD
            );

            txt(
                    c,
                    icon,
                    x + w / 2,
                    y + dp(31),
                    22,
                    col,
                    true,
                    Paint.Align.CENTER
            );

            txt(
                    c,
                    a,
                    x + w / 2,
                    y + dp(53),
                    12,
                    TEXT,
                    true,
                    Paint.Align.CENTER
            );

            txt(
                    c,
                    b,
                    x + w / 2,
                    y + dp(70),
                    9,
                    MUTED,
                    false,
                    Paint.Align.CENTER
            );
        }

        void metric(
                Canvas c,
                float x,
                float y,
                String label,
                String val,
                int col
        ) {

            txt(
                    c,
                    val,
                    x,
                    y,
                    25,
                    col,
                    true,
                    Paint.Align.CENTER
            );

            txt(
                    c,
                    label,
                    x,
                    y + dp(20),
                    10,
                    MUTED,
                    false,
                    Paint.Align.CENTER
            );
        }

        void row(
                Canvas c,
                float l,
                float y,
                float r,
                String title,
                String sub,
                String num,
                int col
        ) {

            rr(
                    c,
                    l,
                    y,
                    r,
                    y + dp(50),
                    dp(16),
                    CARD
            );

            rr(
                    c,
                    r - dp(49),
                    y + dp(8),
                    r - dp(9),
                    y + dp(42),
                    dp(12),
                    Color.argb(35,col,col,col)
            );

            txt(
                    c,
                    num,
                    r - dp(29),
                    y + dp(29),
                    10,
                    col,
                    true,
                    Paint.Align.CENTER
            );

            txt(
                    c,
                    title,
                    r - dp(62),
                    y + dp(22),
                    13,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    sub,
                    r - dp(62),
                    y + dp(39),
                    9,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );
        }

        void title(Canvas c, String a, String b) {

            float r = getWidth() - dp(20);

            txt(
                    c,
                    a,
                    r,
                    dp(113),
                    24,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    b,
                    r,
                    dp(138),
                    11,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );
        }

        void people(Canvas c) {

            title(
                    c,
                    "افراد",
                    "آدم‌ها و مهارت‌هایی که می‌توانند به هم متصل شوند."
            );

            float l = dp(18);
            float r = getWidth() - dp(18);

            rr(
                    c,
                    l,
                    dp(118),
                    r,
                    dp(161),
                    dp(15),
                    CARD
            );

            txt(
                    c,
                    "جستجو در افراد و مهارت‌ها  🔎",
                    r - dp(16),
                    dp(145),
                    11,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );

            card(
                    c,
                    l,
                    dp(176),
                    r,
                    "متخصص / سازنده",
                    "مهارت‌ها، تجربه و همکاری",
                    CYAN
            );

            card(
                    c,
                    l,
                    dp(260),
                    r,
                    "یادگیرنده / استعداد",
                    "مسیر رشد و آموزش",
                    GREEN
            );

            card(
                    c,
                    l,
                    dp(344),
                    r,
                    "تأمین‌کننده / بازار",
                    "کالا، مصالح و منابع",
                    ORANGE
            );

            info(
                    c,
                    l,
                    dp(452),
                    r,
                    "اصل حریم خصوصی",
                    "اطلاعات فقط در سطح لازم برای همکاری نمایش داده می‌شود."
            );
        }

        void projects(Canvas c) {

            title(
                    c,
                    "کار و پروژه",
                    "از نیاز واقعی تا اجرای واقعی."
            );

            float l = dp(18);
            float r = getWidth() - dp(18);

            project(
                    c,
                    l,
                    dp(120),
                    r,
                    "پروژه ساختمانی",
                    "نیرو • مصالح • زمان • کیفیت",
                    "در حال برنامه‌ریزی",
                    CYAN
            );

            project(
                    c,
                    l,
                    dp(225),
                    r,
                    "پروژه آموزشی",
                    "آموزش • استعداد • مسیر کار",
                    "آماده شروع",
                    GREEN
            );

            project(
                    c,
                    l,
                    dp(330),
                    r,
                    "پروژه تولیدی",
                    "تولید • تأمین • بازار",
                    "در انتظار اتصال",
                    ORANGE
            );

            info(
                    c,
                    l,
                    dp(455),
                    r,
                    "موتور روش کار",
                    "روش‌های اجرا، زمان، کیفیت، هزینه و رضایت مشتری ثبت و مقایسه می‌شوند."
            );
        }

        void market(Canvas c) {

            title(
                    c,
                    "بازار و منابع",
                    "عرضه، تقاضا، مصالح، ابزار و منابع راکد."
            );

            float l = dp(18);
            float r = getWidth() - dp(18);

            card(
                    c,
                    l,
                    dp(120),
                    r,
                    "مصالح و تجهیزات",
                    "خرید • فروش • تأمین",
                    CYAN
            );

            card(
                    c,
                    l,
                    dp(215),
                    r,
                    "نیروی متخصص",
                    "کارگر • استادکار • متخصص",
                    GREEN
            );

            card(
                    c,
                    l,
                    dp(310),
                    r,
                    "منابع راکد",
                    "ابزار • موجودی • ظرفیت خالی",
                    ORANGE
            );

            card(
                    c,
                    l,
                    dp(405),
                    r,
                    "سرمایه و پروژه",
                    "تأمین مالی و بازگشت ارزش",
                    CYAN
            );
        }

        void more(Canvas c) {

            title(
                    c,
                    "بیشتر",
                    "ابزارهای اصلی سیستم در یک فضای قابل توسعه."
            );

            float l = dp(18);
            float r = getWidth() - dp(18);

            info(
                    c,
                    l,
                    dp(120),
                    r,
                    "آموزش و استعداد",
                    "مسیر یادگیری، آموزش و اتصال استعداد به فرصت."
            );

            info(
                    c,
                    l,
                    dp(215),
                    r,
                    "امنیت و اعتماد",
                    "حریم خصوصی، ثبت رویداد، هشدار و توقف ایمن."
            );

            info(
                    c,
                    l,
                    dp(310),
                    r,
                    "داده و دانش",
                    "ثبت تجربه‌ها و ساختن حافظه سازمان‌یافته برای RNV."
            );

            info(
                    c,
                    l,
                    dp(405),
                    r,
                    "هماهنگی سیستم",
                    "تحلیل، گزارش، تشخیص خطا و هماهنگی منابع."
            );

            info(
                    c,
                    l,
                    dp(500),
                    r,
                    "تماس با راهاناوند",
                    "پیشنهاد، همکاری، گزارش مشکل یا ارائه یک نیاز واقعی."
            );
        }

        void card(
                Canvas c,
                float l,
                float y,
                float r,
                String a,
                String b,
                int col
        ) {

            rr(
                    c,
                    l,
                    y,
                    r,
                    y + dp(70),
                    dp(18),
                    CARD
            );

            rr(
                    c,
                    r - dp(58),
                    y + dp(14),
                    r - dp(14),
                    y + dp(56),
                    dp(14),
                    Color.argb(32,col,col,col)
            );

            txt(
                    c,
                    "●",
                    r - dp(36),
                    y + dp(41),
                    18,
                    col,
                    true,
                    Paint.Align.CENTER
            );

            txt(
                    c,
                    a,
                    r - dp(72),
                    y + dp(28),
                    14,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    b,
                    r - dp(72),
                    y + dp(47),
                    10,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );
        }

        void project(
                Canvas c,
                float l,
                float y,
                float r,
                String a,
                String b,
                String state,
                int col
        ) {

            rr(
                    c,
                    l,
                    y,
                    r,
                    y + dp(88),
                    dp(19),
                    CARD
            );

            txt(
                    c,
                    a,
                    r - dp(18),
                    y + dp(28),
                    15,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    b,
                    r - dp(18),
                    y + dp(49),
                    10,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );

            rr(
                    c,
                    l + dp(14),
                    y + dp(57),
                    l + dp(105),
                    y + dp(78),
                    dp(10),
                    Color.argb(32,col,col,col)
            );

            txt(
                    c,
                    state,
                    l + dp(60),
                    y + dp(72),
                    9,
                    col,
                    true,
                    Paint.Align.CENTER
            );
        }

        void info(
                Canvas c,
                float l,
                float y,
                float r,
                String a,
                String b
        ) {

            rr(
                    c,
                    l,
                    y,
                    r,
                    y + dp(82),
                    dp(18),
                    CARD
            );

            txt(
                    c,
                    a,
                    r - dp(18),
                    y + dp(29),
                    14,
                    TEXT,
                    true,
                    Paint.Align.RIGHT
            );

            txt(
                    c,
                    b,
                    r - dp(18),
                    y + dp(51),
                    10,
                    MUTED,
                    false,
                    Paint.Align.RIGHT
            );
        }

        void nav(Canvas c) {

            float w = getWidth();
            float h = getHeight();
            float top = h - dp(78);

            rr(
                    c,
                    dp(10),
                    top,
                    w - dp(10),
                    h - dp(10),
                    dp(23),
                    Color.rgb(9,27,41)
            );

            String[] names = {
                    "خانه",
                    "افراد",
                    "پروژه‌ها",
                    "بازار",
                    "بیشتر"
            };

            String[] icons = {
                    "⌂",
                    "●",
                    "◆",
                    "◈",
                    "☰"
            };

            float cell = (w - dp(20)) / 5f;

            for (int i = 0; i < 5; i++) {

                float x = dp(10) + cell * i + cell / 2f;

                int col = i == page ? CYAN : MUTED;

                if (i == page) {

                    rr(
                            c,
                            x - dp(25),
                            top + dp(8),
                            x + dp(25),
                            top + dp(50),
                            dp(18),
                            Color.argb(
                                    35,
                                    CYAN,
                                    CYAN,
                                    CYAN
                            )
                    );
                }

                txt(
                        c,
                        icons[i],
                        x,
                        top + dp(30),
                        17,
                        col,
                        true,
                        Paint.Align.CENTER
                );

                txt(
                        c,
                        names[i],
                        x,
                        top + dp(52),
                        9,
                        col,
                        i == page,
                        Paint.Align.CENTER
                );
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {

            if (e.getAction() == MotionEvent.ACTION_DOWN) {

                downX = e.getX();
                downY = e.getY();

                return true;
            }

            if (e.getAction() == MotionEvent.ACTION_UP) {

                float x = e.getX();
                float y = e.getY();

                if (
                        Math.abs(x - downX) < dp(25)
                                &&
                        Math.abs(y - downY) < dp(25)
                ) {

                    if (y > getHeight() - dp(95)) {

                        int selected =
                                (int) (x / (getWidth() / 5f));

                        if (selected < 0) {
                            selected = 0;
                        }

                        if (selected > 4) {
                            selected = 4;
                        }

                        page = selected;

                        invalidate();

                        setContentDescription(
                                "راهاناوند، بخش "
                                        +
                                new String[]{
                                        "خانه",
                                        "افراد",
                                        "پروژه‌ها",
                                        "بازار",
                                        "بیشتر"
                                }[page]
                        );

                        sendAccessibilityEvent(
                                AccessibilityEvent
                                        .TYPE_WINDOW_CONTENT_CHANGED
                        );

                        return true;
                    }

                    if (
                            page == 0
                                    &&
                            y >= dp(190)
                                    &&
                            y <= dp(245)
                    ) {

                        Toast.makeText(
                                MainActivity.this,
                                "مسیر RNV آماده توسعه است.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }

                return true;
            }

            return true;
        }
    }
}
