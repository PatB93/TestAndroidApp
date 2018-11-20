package com.example.pbode.brflickr.viewmodels;

import android.graphics.drawable.Drawable;

import com.example.pbode.brflickr.R;
import com.example.pbode.brflickr.service.ImageDetails;
import com.example.pbode.brflickr.service.ImageUrlBuilder;
import com.example.pbode.brflickr.utils.GlideProvider;
import com.example.pbode.brflickr.utils.UiResourceProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageDetailsViewModelTest {
    @Mock
    private GlideProvider glideProvider;

    @Mock
    private UiResourceProvider uiResourceProvider;

    private final ImageUrlBuilder imageUrlBuilder = new ImageUrlBuilder();
    private final ImageDetails imageDetails = new ImageDetails("id", "owner", "secret", "server", 1, "title", 1, 1, 1);
    private ImageDetailsViewModel subject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Drawable drawable = mock(Drawable.class);
        when(uiResourceProvider.getImage(R.drawable.ic_image_not_found)).thenReturn(drawable);

        subject = new ImageDetailsViewModel(imageDetails, glideProvider, uiResourceProvider);
    }

    @Test
    public void fetchImage_getThumbnailImage() {
        String url = imageUrlBuilder
                .farm(imageDetails.getFarm())
                .serverId(imageDetails.getServer())
                .photoId(imageDetails.getId())
                .secret(imageDetails.getSecret())
                .thumbnail()
                .build();

        verify(glideProvider).loadUrlToTarget(url, subject.thumbnail);
    }

    @Test
    public void fetchImage_getFullImage() {
        String url = imageUrlBuilder
                .farm(imageDetails.getFarm())
                .serverId(imageDetails.getServer())
                .photoId(imageDetails.getId())
                .secret(imageDetails.getSecret())
                .mediumImage()
                .build();

        verify(glideProvider).loadUrlToTarget(url, subject.fullImage);
    }
}